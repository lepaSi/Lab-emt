import React from "react";
import {useHistory} from "react-router-dom";

const Bookadd = (props) => {

    const history = useHistory();
    const [formData, uptadeFormData] = React.useState({
        name: "",
        category: "",
        author: 1,
        availableCopies: 0
    })
    const handleChange = (e) => {
        uptadeFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })

    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const author = formData.author;
        const availableCopies = formData.availableCopies;

        props.onAddBook(name,category,author,availableCopies);
        history.push("/books");

    }
    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter book name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className='form-group col-4'>
                        <label htmlFor='bookCategories'>Category</label>
                        <select name='category' required={true} className='form-control mt-1' id='categories'
                                onChange={handleChange} defaultValue={''}>
                            <option value='' hidden>Select Category</option>
                            {props.categories.map(category => {
                                return (
                                    <option value={category}>{category}</option>
                                );
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.name} {term.surname}</option>
                            )}
                        </select>
                    </div>
                    <div className='form-group'>
                        <label>Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               required
                               placeholder="Avaliable Copies"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

    )
}
export default Bookadd;