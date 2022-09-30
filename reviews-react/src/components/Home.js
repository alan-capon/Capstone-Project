import './Home.css'

// Home page upon startup

function Home(){
    return(
        <>
        {/* <h2 className='realHome'>(Main Page)</h2> */}
        <div className='Here'> Welcome to TrustUs</div>
        <div className='Body'> The Future of <br/>Customer Reviews</div>
        <div className='Button'>
        <a class="btn btn-primary btn-lg" href="/products" role="button">Explore Now</a>
        </div>
        <div className='forAll'>
        <div className='container'>
            <div className='ring'></div>
            <div className='ring'></div>
            <div className='ring'></div>
        </div>
        </div>
        </>
    )
}

export default Home;