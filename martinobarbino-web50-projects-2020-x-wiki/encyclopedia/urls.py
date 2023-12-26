from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("wiki/<str:title>", views.entry_page, name="entry_page"),
    path("search", views.search, name="search"),
    path("new", views.new, name="new"),
    path("edit/<str:title>", views.edit, name="edit"),
    path("random", views.random_page, name="random")
]
