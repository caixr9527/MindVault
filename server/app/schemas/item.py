from typing import Optional
from pydantic import BaseModel, Field


class ItemBase(BaseModel):
    name: str
    description: Optional[str] = None
    price: float = Field(..., gt=0, description="The price must be greater than zero")
    is_offer: Optional[bool] = None


class ItemCreate(ItemBase):
    pass


class ItemUpdate(ItemBase):
    name: Optional[str] = None
    price: Optional[float] = Field(None, gt=0, description="The price must be greater than zero")


class ItemInDBBase(ItemBase):
    id: int
    created_at: str
    updated_at: str

    class Config:
        from_attributes = True


class Item(ItemInDBBase):
    pass


class ItemInDB(ItemInDBBase):
    pass
