import "./ProductsForm.css"
import React, { useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import AuthContext from "../AuthContext";

const PRODUCT_DEFAULT = {
    name: "",
    description: ""
}

function ProductsForm() {
    const [product, setProduct] = useState(PRODUCT_DEFAULT);
    const auth = useContext(AuthContext);

    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();

    const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(product)
        }

        fetch('http://localhost:8080/api/products', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json()
                } else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => {
                if(data.id) {
                    history.push("/products");
                } else {
                    //seterrors
                }
            })
            .catch(console.log);
    }

    const handleChange = (event) => {
        const newProduct = {...product}

        newProduct[event.target.name] = event.target.value;

        setProduct(newProduct);
    }

    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <div className="form-content">
                    <h3 className="form-title">Add Product</h3>
                    <div className="form-group mt-3">
                        <label>Product Name:</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            className="form-control mt-1"    
                            value={product.name}   
                            onChange={handleChange}                                          
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Description</label>
                        <input
                            type="text"
                            id="description"
                            name="description"
                            className="form-control mt-1"
                            value={product.description}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="d-grid gap-2 mt-3 mb-3">
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </div>
            </form>
        </div>
    )    
}

export default ProductsForm;