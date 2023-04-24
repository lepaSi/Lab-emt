import axios from '../custom-axios/axios';

const ELibraryService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries")
    },
    fetchBooks: () =>{
        return axios.get("/books")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);// so $id se zema dadenoto id
    },
    addBook: (name,category,author,availableCopies) => {
        return axios.post("/books/add",{
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    editBook: (id,name,category,author,availableCopies) => {
        return axios.put(`/books/edit/${id}`,{
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markAsTaken: (id) =>{
        return axios.put(`/books/markastaken/${id}`)
    }
}
export default ELibraryService;