from sqlalchemy import Column, Float, Integer, String

from app.db.base import BaseModel


class Item(BaseModel):
    __tablename__ = "items"
    
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    description = Column(String, index=True, nullable=True)
    price = Column(Float, index=True)
    is_offer = Column(Integer, default=0)
