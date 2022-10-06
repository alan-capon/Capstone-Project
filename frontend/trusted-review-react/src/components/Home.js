import {Link} from 'react-router-dom';
import './Home.css'

// // Home page upon startup

function Home(){
    return(
        <>
        {/* <h2 className='realHome'>(Main Page)</h2> */}
        <div className='Here'> Welcome to TrustedReviews</div>
        <div className='Body'> The Future of <br/>Customer Reviews</div>
        <div className='Button'>
        <Link className="btn btn-primary btn-lg" to="/products">
                Explore Now
            </Link>

        </div>
        <div className='forAll'>
        <div className='container'>
            
            <div className='ring1'></div>
            <div className='ring2'></div>
            <div className='ring3'></div>
            <div className='ring4'></div>
            <div className='ring5'></div>

            {/* <div className='ring'></div>
            <div className='ring'></div>
            <div className='ring'></div> */}
        </div>
        </div>
        </>
    )
}

export default Home;