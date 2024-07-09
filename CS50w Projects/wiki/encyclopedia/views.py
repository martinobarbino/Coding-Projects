from django.shortcuts import render
import markdown2
import random
from . import util


def index(request):
    return render(request, "encyclopedia/index.html", {
        "entries": util.list_entries()
    })

def entry_page(request, title):
    
    # Case for if there is no entry of particular title
    if util.get_entry(title) is None:
        return render(request, "encyclopedia/error.html", {
            "message": "Requested page not found."
        })
    
    else:
        # Load converted md file as wiki entry
        return render(request, "encyclopedia/page.html", {
            "title": title,
            "text": markdown2.markdown(util.get_entry(title))
        })

def search(request):
    
    # Detects if user submitted form
    if request.method == "POST":
        
        # Content of user's search
        query = request.POST['q']
        
        # Case for if the query does not match an entry
        if util.get_entry(query) is None:
            entries = util.list_entries()
            results = []
            for entry in entries:
                if query.lower() in entry.lower():
                    results.append(entry)
            return render(request, "encyclopedia/search.html", {
                "results": results
            })
            
        # Case for if query matches a result    
        else: 
            return render(request, "encyclopedia/page.html", {
            "title": query,
            "text": markdown2.markdown(util.get_entry(query))
            })
            
def new(request):

    # Load page creation page
    if request.method == "GET":
        return render(request, "encyclopedia/new.html")
    
    # Detect form submission
    else:
        title = request.POST['title']
        content = request.POST['entryText']
        
        # Detect entry of duplicate title
        if util.get_entry(title) is not None:

            return render(request, "encyclopedia/error.html", {
                "message": "An entry with that name already exists."
            })
        
        else:
            util.save_entry(title, content)
            return render(request, "encyclopedia/page.html", {
                "title": title,
                "text": markdown2.markdown(util.get_entry(title))
            })

def edit(request, title):
    
    # Case for link being clicked
    if request.method == "GET":
        return render(request, "encyclopedia/edit.html", {
            "title": title,
            "content": util.get_entry(title)
        })
    
    # Case for form submission
    if request.method == "POST":
       
       # Save edited content
       content = request.POST["editedText"]
       util.save_entry(title, content)
       
       #Render page with new content
       return render(request, "encyclopedia/page.html", {
           "title": title,
           "text": markdown2.markdown(util.get_entry(title))
       }) 
        
def random_page(request):
    
    # Function for randomly selecting entry
    entries = util.list_entries()
    title = random.choice(entries)
    
    # Render random entry
    return render(request, "encyclopedia/page.html", {
        "title": title,
        "text": markdown2.markdown(util.get_entry(title))
    })
                
                
        
