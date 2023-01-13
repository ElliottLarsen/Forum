# Author: Elliott Larsen
# Date:
# Description:

import datetime

from pydantic import BaseModel

class Post(BaseModel):
    id: int
    subject: str
    content: str
    create_date: datetime.datetime

    class Config:
        # Automatically maps Post attributes.
        orm_mode = True

