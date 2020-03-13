import java.util.Iterator;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
	private MyLinkedList<Genre> Data;
    public MovieDB() {
    	Data = new MyLinkedList<>();
    	// Data consists of nodes of Genre
    	// Genre node consists of genre name(String) and a linked list of titles(MovieList)
    }

    public void insert(MovieDBItem item) {
    	for(Genre genre : Data){								// For all genres,
    		if(genre.getItem().equals(item.getGenre())){		
    			for(String title : genre.movieList){			// and for all titles,
    				if(title.equals(item.getTitle())) return;	// if there is already the same data, do nothing.
    			}
    			genre.add(item);								// if there is already the same genre,
    			return;											// insert item into it.
    		}
    	}
    	Genre newGenre = new Genre(item.getGenre());			// if no genre matched, make a new genre for the item
    	newGenre.add(item);										// and insert it.
    	add(newGenre);			// void add(Genre item) is defined
    							// at the bottom of this class.
    							// This method adds "Genre" nodes.
    }

    public void delete(MovieDBItem item) {
    	Iterator<Genre> genreIter = Data.iterator();	// Iterator for genre
    	while(genreIter.hasNext()){						// Find the same genre as item has
    		Genre g = genreIter.next();
    		if(item.getGenre().equals(g.getItem())){	// If found,
				Iterator<String> titleIter = g.movieList.iterator();	// Make a new iterator for title
				while(titleIter.hasNext()){
					if(item.getTitle().equals(titleIter.next())){		// And find the title
						titleIter.remove();								// If found, remove it and break
						break;
					}
				}
				titleIter = g.movieList.iterator();		// Reset titleIter back to the head
				if(!titleIter.hasNext())				// To check if there is no more movie with the searching genre
					genreIter.remove();					// because if then it is supposed to be removed.
    		}
    	}
    }

    public MyLinkedList<MovieDBItem> search(String term) {
    	MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
    	for(Genre g : Data){						// For all genres
    		for(String title : g.movieList){		// and titles
    			if(title.contains(term)){			// if title contains the term
    				MovieDBItem newItem = new MovieDBItem(g.getItem(), title);
    				results.add(newItem);			// add results the MovieDBItem with such genre and title
    			}
    		}
    	}
        return results;								// and return results.
    }
    
    public MyLinkedList<MovieDBItem> items() {   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for (Genre genre : Data){							// For all genres
        	for (String title : genre.movieList){			// and titles
        		MovieDBItem newItem = new MovieDBItem(genre.getItem(), title);
        		results.add(newItem);		// add to results the MovieDBItem with such genre and title
        	}
        }
    	return results;						// and return results.
    }
    
    public void add(Genre item){
    	// This method adds "Genre" nodes made by item to Data(MyLinkedList<Genre>)
    	// in lexicographical order.
    	// Codes are similar to MovieList.add(String item),
    	// which adds "Title"(String) to the MovieList
    	// (NOT TO BE CONFUSED)
		Node<Genre> curr = Data.head;
		Node<Genre> next = curr.getNext();
		while(next != null && item.compareTo(next.getItem()) > 0){
			curr = next;
			next = next.getNext();
		}
		if(next != null && item.equals(next.getItem()))
			return;
		curr.insertNext(item);
		Data.numItems += 1;
	}
}

class Genre extends Node<String> implements Comparable<Genre> {
	public MovieList movieList;
	public Genre(String name) {
		super(name);
		movieList = new MovieList();
	}
	
	// 'compareTo', 'equals' methods are used to compare Genre nodes
	// by the name of genre.
	// Those are used in the methods of MovieDB class.
	
	@Override
	public int compareTo(Genre o) {
		return getItem().compareTo(o.getItem());		// Compares only genre name.
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("not used");
	}

	@Override
	public boolean equals(Object obj) {					// Compares only genre name.
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Genre other = (Genre) obj;
        if (getItem() == null) {
            if (other.getItem() != null)
                return false;
        } else if (!getItem().equals(other.getItem()))
            return false;
        return true;
	}
	
	public void add(MovieDBItem item){			// Adds "Title" to the movieList.
		movieList.add(item.getTitle());
	}
}

class MovieList implements ListInterface<String> {	
	private MyLinkedList<String> movieList;
	public MovieList() {
		movieList = new MyLinkedList<String>();
	}

	@Override
	public Iterator<String> iterator() {
		return movieList.iterator();
	}

	@Override
	public boolean isEmpty() {
		return movieList.isEmpty();
	}

	@Override
	public int size() {
		return movieList.size();
	}

	@Override
	public void add(String item) {
		// Adds item to the movieList in lexicographical order.
		Node<String> curr = movieList.head;		// Start at the head
		Node<String> next = curr.getNext();
		while(next != null && item.compareTo(next.getItem()) > 0){
			curr = next;
			next = next.getNext();
		}							// and go next
									// until the input item precedes the curr item.
		if(next != null && item.equals(next.getItem()))
			return;					// If there already exists such item, do nothing.
		curr.insertNext(item);
		movieList.numItems += 1;	// Insert the item.
	}

	@Override
	public String first() {
		return movieList.first();
	}

	@Override
	public void removeAll() {
		movieList.removeAll();
	}
}