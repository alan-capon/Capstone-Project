import "./SignIn.css";
import React, { useState, useContext } from 'react';
import AuthContext from '../AuthContext';

function SignIn() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const auth = useContext(AuthContext);

    const history = useHistory();


    const handleSubmit = (event) => {
        event.preventDefault();

        const authAttempt = {
            username,
            password
        }

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(authAttempt)
        }

        fetch('http://localhost:8080/api/authenticate', init)
            .then(response => {
                if (response.status === 200) {
                    return response.json(); //this will be token
                } else if (response.status === 403) {
                    return null;
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data) {
                    // {
                    //   "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjYWxvcmllLXRyYWNrZXIiLCJzdWIiOiJzbWFzaGRldjUiLCJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsImV4cCI6MTYwNTIzNDczNH0.nwWJtPYhD1WlZA9mGo4n5U0UQ3rEW_kulilO2dEg7jo"
                    // }
                    auth.login(data.jwt_token);
                    history.push('/');
                } else {
                    //errors
                }
            })
            .catch(console.log);
    };

    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <div className="form-content">
                    <h3 className="form-title">Sign In</h3>
                    <div className="form-group mt-3">
                        <label>Username</label>
                        <input
                            type="text"
                            className="form-control mt-1"
                            placeholder="Enter username"
                            onChange={(event) => setUsername(event.target.value)}
                            value={username}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Password</label>
                        <input
                            type="password"
                            className="form-control mt-1"
                            placeholder="Enter password"
                            onChange={(event) => setPassword(event.target.value)}
                            value={password}
                        />
                    </div>
                    <div className="d-grid gap-2 mt-3">
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                    </div>
                    <p className="text-center mt-2">
                        <a href="/register">Don't have an account?</a>
                    </p>
                </div>
            </form>
        </div>
    )
}

export default SignIn;