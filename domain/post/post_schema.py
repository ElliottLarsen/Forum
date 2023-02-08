# Author: Elliott Larsen
# Date:
# Description:

import datetime
from pydantic import BaseModel
from domain.comment.comment_schema import Comment

class Post(BaseModel):
    id: int
    subject: str
    content: str
    create_date: datetime.datetime
    comments: list[Comment] = []

    class Config:
        # Automatically maps Post attributes.
        orm_mode = True

