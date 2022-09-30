import "./Products.css";

function Products() {
    return (
        <>
            <div className="Products">
                <h1 className="Title">TrustUs</h1>
                <h2 className="Description">Discover reviews on the latest products</h2>
                <div className="input-group mx-auto w-25" id="Search">
                    <input type="text" className="text-center rounded" placeholder="Enter a Product"/>
                        <div class="input-group-append">
                            <button class="btn btn-success" type="submit">Search</button>
                        </div>
                </div>
            </div>
        </>

    )
}

export default Products;