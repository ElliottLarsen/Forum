# Author: Elliott Larsen
# Date:
# Description:

from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from domain.post import post_router

app = FastAPI()

origins = [
    "http://localhost:5173",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins = origins,
    allow_credentials = True,
    allow_methods = ["*"],
    allow_headers = ["*"],
)

# Register routers.
app.include_router(post_router.router)
