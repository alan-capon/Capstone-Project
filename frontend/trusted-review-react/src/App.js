import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/Home';
import About from './components/About';
import TrustReviewList from './components/TrustReviewList';
import TrustReviewForm from './components/TrustReviewForm';
import NotFound from './components/NotFound';
import TrustedReviewPage from './components/TrustedReviewPage';
import SignIn from './components/SignIn';
import Products from './components/Products'
import SignUp from './components/SignUp';

function App() {
  return (
    <>
      <Router>
        {/*Unesscary title now -> <h1>Trusted Reviews</h1> */}
        <Navbar/>
        <Switch>
          <Route path="/" exact>
            <Home/>
          </Route>
          <Route path="/register" exact>
            <SignUp/>
          </Route>          
          <Route path="/about" exact>
            <About/>
          </Route>
          <Route path="/products" exact>
            <Products/>
          </Route>
          <Route path="/trustedreviews" exact>
            <TrustedReviewPage/>
          </Route>          
          {/* <Route path={["/trustedreviews/add","/trustedreviews/edit/:id"]}>
            <TrustedReviewForm/>
          </Route> */}
          {/* <Route path="/trustedreviews" exact>
            <TrustedReviewList/>
          </Route> */}
            <Route path="/login" exact>
              <SignIn/>
            </Route>
          <Route>
            <NotFound/>
          </Route>         
        </Switch>       
      </Router>      
    </>
  );
}

export default App;