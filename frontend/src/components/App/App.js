import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router,Redirect,Route} from "react-router-dom";
import Authors from "../Authors/authors";
import Books from "../Books/BookList/books";
import Countries from "../Countries/countries";
import BookAdd from "../Books/BookAdd/bookadd"
import ELibraryService from "../../repository/elibraryRepository";
import Header from "../Header/header";
import Categories from "../Categories/categories";
import BookEdit from "../Books/BookEdit/bookEdit";




class App extends Component{
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            countries: [],
            books: [],
            categories: [],
            selectedBook: {}

        };
    }
    render() {
        return (
            // mesto kade gi spesificirame dadeni ruti
          <Router>
              <Header/>
              <main>
                <div className="container">
                    <Route path={"/authors"} exact render={() =>
                        <Authors authors = {this.state.authors}/>}/>
                    <Route path={"/countries"} exact render={() =>
                        <Countries countries = {this.state.countries}/>}/>
                    <Route path={"/categories"} exact render={() =>
                        <Categories categories = {this.state.categories}/>}/>
                    <Route path={"/books/add"} exact render={() =>
                        <BookAdd authors = {this.state.authors}
                                 categories = {this.state.categories}
                                 onAddBook = {this.addBook}/>}/>
                    <Route path={"/books/edit/:id"} exact render={() =>
                        <BookEdit authors = {this.state.authors}
                                  categories = {this.state.categories}
                                  onEditBook = {this.editBook}
                                  book = {this.state.selectedBook}/>}/>
                    <Route path={"/books"} exact render={() =>
                        <Books books = {this.state.books}
                               onDelete={this.deleteBook}
                               onmarkAsTaken={this.markAsTaken}
                               onEdit={this.getBook}/>}/>
                    <Redirect to={"/books"}/>

                </div>
              </main>
          </Router>
        );
    }

    loadAuthors = () => {
        ELibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    loadBooks = () => {
        ELibraryService.fetchBooks()
            .then((data) =>{
                this.setState({
                    books: data.data
                })
            });
    }
    loadCountries = () => {
        ELibraryService.fetchCountries()
            .then((data) =>{
                this.setState({
                    countries: data.data
                })
            });
    }
    loadCategories = () => {
        ELibraryService.fetchCategories()
            .then((data) =>{
                this.setState({
                    categories: data.data
                })
            })
    }
    deleteBook = (id) => {
        ELibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }
    addBook = (name, category, author, avaiableCopies) => {
        ELibraryService.addBook(name,category,author,avaiableCopies)
            .then(() => {
                this.loadBooks()
            });
    }
    getBook = (id) => {
        ELibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    editBook = (id,name,category,author,availableCopies) => {
        ELibraryService.editBook(id,name,category,author,availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    markAsTaken = (id) => {
        ELibraryService.markAsTaken(id)
        .then(() =>{
            this.loadBooks();
        })
    }
    componentDidMount() {
        this.loadAuthors();
        this.loadCountries();
        this.loadBooks();
        this.loadCategories();
    }
}

export default App;
