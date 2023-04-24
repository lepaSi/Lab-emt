import React from "react";
import BookTerm from "../BookTerm/bookTerm";
import {Link} from "react-router-dom";
import ReactPaginate from "react-paginate";


class Books extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 3
        }
    }
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const books = this.getBooksPage(offset,nextPageOffset);
        const pageCount = Math.ceil(this.props.books.length / this.state.size)
        return(
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>AvailableCopies</th>
                            </tr>
                            </thead>
                            <tbody>
                                {books}
                            </tbody>
                        </table>
                    </div>
                    <div className={"col mb-3"}>
                        <div className={"row"}>
                            <div className={"col-sm-12 col-md-12"}>
                                <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new Book</Link>
                            </div>
                        </div>
                    </div>

                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-6 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }
    getBooksPage = (offset,nextPageOffset) => {

        return this.props.books.map((term) => {
            return (
                <BookTerm term={term} onDelete={this.props.onDelete} onmarkAsTaken={this.props.onmarkAsTaken} onEdit={this.props.onEdit}/>
            );
        }).filter((book,index) =>{
            return index >= offset && index < nextPageOffset;
        })

    }
}
export default Books;