# Author: Elliott Larsen
# Date:
# Description:

import datetime
from pydantic import BaseModel, validator

class CommentCreate(BaseModel):
    content: str

    @validator("content")
    def is_empty(cls, v):
        if not v or not v.strip():
            raise ValueError("Empty fields are not allowed.")
        return v

class Comment(BaseModel):
    id: int
    content: str
    create_date: datetime.datetime

    class Config:
        orm_mode = True