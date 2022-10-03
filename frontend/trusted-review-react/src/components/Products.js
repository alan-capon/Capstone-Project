import "./Products.css";
import { useEffect, useState } from 'react';


function Products() {
    const [products, setProducts] = useState([]);
    const [filterdProduct, setFilteredProduct] = useState(products);
    const [input, setInput] = useState("");

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
                value={input} onChange={handleChange}/>
                <button className="btn btn-success" type="submit" onClick={() => handleSearch(input)}>
                    Search
                </button>
            </div>
            <div className="container-fluid mt-5 text-center">
                <div className="row justify-content-center">
                    <div className="col-md-4">
                        {filterdProduct.map(product => (
                            <div class="card" key={product.id}>
                            <img src="/favicon.ico" class="card-img-top" alt="" />
                            <div class="card-body">
                                <h5 class="card-title">{product.name}</h5>
                                <p class="card-text">{product.description}</p> 
                                <a href="" >View Reviews</a>
                            </div>
                        </div>
                        ))}
                        
                    </div>

                    {/* <div className="col-md-4">
                        <div class="card">
                            <img src="/favicon.ico" class="card-img-top" alt="" />
                            <div class="card-body">
                                <h5 class="card-title">Title</h5>
                                <p class="card-text">Some text</p> 
                                <a href="" >View Reviews</a>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div class="card">
                            <img src="/favicon.ico" class="card-img-top" alt="" />
                            <div class="card-body">
                                <h5 class="card-title">Title</h5>
                                <p class="card-text">Some text</p> 
                                <a href="" >View Reviews</a>
                            </div>
                        </div>
                    </div> */}

                   
                </div>
            </div>



           
        </>

    )
}

export default Products;