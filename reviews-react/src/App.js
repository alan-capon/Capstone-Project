import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/Home';
import About from './components/About';
import TrustReviewList from './components/TrustReviewList';
import TrustReviewForm from './components/TrustReviewForm';
import NotFound from './components/NotFound';
import SignIn from './components/SignIn';
import Products from './components/Products'
import SignUp from './components/SignUp';
import AuthContext from "./AuthContext";
import { useState } from 'react';
import jwt_decode from "jwt-decode";
import ProductsForm from "./components/ProductsForm";
import EditForm from "./components/EditForm";

function App() {
  const[user, setUser] = useState(null);

  const login = (token) => {
    const decodedToken = jwt_decode(token);

    const roles = decodedToken.authorities.split(",");

    const userToLogin = {
      username: decodedToken.sub,
      roles,
      token,
      hasRole(role){
        return this.roles.includes(role);
      }
    }
    setUser(userToLogin);
  }

  const logout = () => {
    setUser(null);
  }

  const auth = {
    user,
    login,
    logout
  }


  return (
    <>
      <AuthContext.Provider value={auth}>
      <Router>

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
          <Route path="/products/add" exact>
              <ProductsForm />
          </Route>
          {/* <Route path="/trustedreviews" exact>
            <TrustedReviewPage/>
          </Route>           */}
          <Route path={["/review/add/:id"]}>
            <TrustReviewForm/>
          </Route>
          <Route path={["/product/reviews/:id"]} exact>
            <TrustReviewList/>
          </Route>
          <Route path={["/review/edit/:id"]} exact>
            <EditForm />
          </Route>
            <Route path="/login" exact>
              <SignIn/>
            </Route>
          <Route>
            <NotFound/>
          </Route>         
        </Switch>       
      </Router>
      </AuthContext.Provider>      
    </>
  );
}

export default App;