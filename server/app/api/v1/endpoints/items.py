from typing import List

from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app.api.dependencies import get_db
from app.crud.base import BaseService
from app.models.item import Item
from app.schemas.item import Item as ItemSchema
from app.schemas.item import ItemCreate, ItemUpdate

router = APIRouter()
crud = BaseService(Item)


@router.get("/", response_model=List[ItemSchema])
def read_items(
    db: Session = Depends(get_db), skip: int = 0, limit: int = 100
) -> List[ItemSchema]:
    """
    Retrieve items.
    """
    items = crud.get_multi(db, skip=skip, limit=limit)
    return items


@router.post("/", response_model=ItemSchema)
def create_item(
    *, db: Session = Depends(get_db), item_in: ItemCreate
) -> ItemSchema:
    """
    Create new item.
    """
    item = crud.create(db=db, obj_in=item_in)
    return item


@router.get("/{item_id}", response_model=ItemSchema)
def read_item(
    *, db: Session = Depends(get_db), item_id: int
) -> ItemSchema:
    """
    Get item by ID.
    """
    item = crud.get(db=db, id=item_id)
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    return item


@router.put("/{item_id}", response_model=ItemSchema)
def update_item(
    *, db: Session = Depends(get_db), item_id: int, item_in: ItemUpdate
) -> ItemSchema:
    """
    Update an item.
    """
    item = crud.get(db=db, id=item_id)
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    item = crud.update(db=db, db_obj=item, obj_in=item_in)
    return item


@router.delete("/{item_id}", response_model=ItemSchema)
def delete_item(
    *, db: Session = Depends(get_db), item_id: int
) -> ItemSchema:
    """
    Delete an item.
    """
    item = crud.get(db=db, id=item_id)
    if not item:
        raise HTTPException(status_code=404, detail="Item not found")
    item = crud.remove(db=db, id=item_id)
    return item
