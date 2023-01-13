# Author: Elliott Larsen
# Date:
# Description:

from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from database import get_db
from domain.post import post_schema, post_crud
from models import Post

router = APIRouter(
    prefix="/forum/post"
)

@router.get("/list", response_model = list[post_schema.Post])
def post_list(db: Session = Depends(get_db)):
    """
    Routes to the list of posts.
    """
    post_list = post_crud.get_post_list(db)
    return post_list