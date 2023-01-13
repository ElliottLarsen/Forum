# Author: Elliott Larsen
# Date:
# Description:

from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from database import get_db
from models import Post

router = APIRouter(
    prefix="/forum/post"
)

@router.get("/list")
def post_list(db: Session = Depends(get_db)):
    post_list = db.query(Post).order_by(Post.create_date.desc()).all()
    return post_list