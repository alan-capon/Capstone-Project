import "./SignUp.css";
import React, { useState, useContext } from 'react';

const USER_DEFAULT = {

}

function SignUp() {
    

    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();


    }

    const handleChange = (event) => {

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
                            className="form-control mt-1"
                            onChange={handleChange}
                            // value={username}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>First Name:</label>
                        <input
                            type="text"
                            className="form-control mt-1"
                            onChange={handleChange}
                            // value={fname}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Last Name:</label>
                        <input
                            type="text"
                            className="form-control mt-1"
                            onChange={handleChange}
                            // value={lnmae}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Email address:</label>
                        <input
                            type="email"
                            className="form-control mt-1"
                            onChange={handleChange}
                            // value={email}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Password:</label>
                        <input
                            type="password"
                            className="form-control mt-1"
                            onChange={handleChange}
                            // value={password}
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