from app.db.session import Base


class BaseModel(Base):
    __abstract__ = True
