import "./SignUp.css";
import React, { useState } from 'react';
import { useHistory } from "react-router-dom";

const USER_DEFAULT = {
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    roles: ["User"]
}

function SignUp() {
    const [user, setUser] = useState(USER_DEFAULT);

    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();
        
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }

        fetch('http://localhost:8080/api/user', init)
            .then(respone => {
                if (respone.status === 201) {
                    return respone.json();
                } else {
                    //errors
                }
            })
            .then(data => {
                if(data) {
                    history.push("/login")
                } else {
                    //errors
                }
            })
            .catch(console.log);
    }

    const handleChange = (event) => {
        const newUser = {...user}

        newUser[event.target.name] = event.target.value;

        setUser(newUser);
    }


    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <div className="form-content">
                    <h3 className="form-title">Sign Up</h3>
                    <div className="form-group mt-3">
                        <label>Username:</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            className="form-control mt-1"
                            value={user.username}
                            onChange={handleChange}                          
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>First Name:</label>
                        <input
                            type="text"
                            id="firstName"
                            name="firstName"
                            className="form-control mt-1"
                            onChange={handleChange}
                            value={user.firstName}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Last Name:</label>
                        <input
                            type="text"
                            id="lastName"
                            name="lastName"
                            className="form-control mt-1"
                            onChange={handleChange}
                            value={user.lastName}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Email address:</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            className="form-control mt-1"
                            onChange={handleChange}
                            value={user.email}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Password:</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            className="form-control mt-1"
                            onChange={handleChange}
                            value={user.password}
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

export default SignUp;