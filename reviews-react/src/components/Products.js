import "./Products.css";
import { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../AuthContext';

function Products() {
    const [products, setProducts] = useState([]);
    const [filterdProduct, setFilteredProduct] = useState(products);
    const [input, setInput] = useState("");
    const auth = useContext(AuthContext);

    useEffect(() => {
        fetch("http://localhost:8080/api/products")
            .then(response => response.json())
            .then(data => {
                setProducts(data);
                setFilteredProduct(data)
            })
            .catch(console.log)
    }, [])

    const handleChange = (event) => {
        setInput(event.target.value)
    }

    const handleSearch = (input) => {
        fetch(`http://localhost:8080/api/products/${input}`)
            .then(response => {
                if (response.status === 200) {
                    const product = products.filter(products => products.name === input)
                    setFilteredProduct(product);
                } else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .catch(console.log)
    }

    return (
        <>
            <div className="Products">
                <h1 className="Title">TrustedReviews</h1>
                <h2 className="Description">Discover reviews on the latest products</h2>
                <input type="text" className="rounded mt-4 w-25 text-center" placeholder="Enter a Product"
                    value={input} onChange={handleChange} />
                <button className="btn btn-primary" type="submit" onClick={() => handleSearch(input)}>
                    Search
                </button>
            </div>
            {auth.user && (
                <div className="add mt-3">
                    <Link className="btn btn-success" to="/products/add">
                        Add a Product
                    </Link>
                </div>
            )}
            <div className="container-fluid mt-5 text-center">
                <div className="row justify-content-center">
                    <div className="col-md-4">
                        {filterdProduct.map(product => (
                            <div class="card" key={product.id}>
                                <img src="/favicon.ico" class="card-img-top" alt="" />
                                <div class="card-body">
                                    <h5 class="card-title">{product.name}</h5>
                                    <p class="card-text">{product.description}</p>
                                    <Link to={`/product/reviews/${product.id}`}>
                                        View Reviews
                                    </Link>
                                    {!auth.user && (
                                        <Link className="addReview" to={`/review/add/${product.id}`}>
                                            Add Review
                                        </Link>                                 
                                    )}
                                </div>
                            </div>
                        ))}

                    </div>
                </div>
            </div>
        </>

    )
}

export default Products;