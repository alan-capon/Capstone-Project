import {Link} from 'react-router-dom';
import './Home.css'
import { Howl } from "howler"
import Song1 from './Alone.mp3'
import Song2 from './BounceGen.mp3'
import Song3 from './CantStopMe.mp3'
import Song4 from './TakeMeHome.mp3'
import Song5 from './Kungs.mp3'
import Song6 from './ShawnMendes.mp3'
import Song7 from './OneDay.mp3'
import Song8 from './R3HABandIconaPop.mp3'
import Song9 from './GetOutMyHead.mp3'
import Song10 from './3LAU.mp3'

// // Home page upon startup

function Home(){
    
    const randomNum = Math.floor(Math.random() * 10);
    console.log(randomNum)
    const songArray = [Song1, Song2, Song3, Song4, Song5, Song6, Song7, Song8, Song9, Song10]
    const sound = new Howl({
        src: [songArray[randomNum]],
        volume: 0.05,
        html5: true,});
    
    
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

        <div className="AllOptions">
             <div className = "btn btn-primary PlayMusic" onClick= {() => sound.play()}>Play Featured</div>
             <div></div>
             <div className = "btn btn-primary PauseMusic" onClick= {() => sound.pause()}>Pause</div>
             <div></div>
             <div className = "btn btn-primary StopMusic" onClick= {() => sound.stop()}>Stop</div>
             </div> 
           

        </>
    )
}

export default Home;