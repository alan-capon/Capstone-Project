import { useState, useContext, useEffect } from "react";
import { useHistory, useParams } from "react-router-dom";
import AuthContext from "../AuthContext";


function TrustReviewForm() {

    const auth = useContext(AuthContext);

    const [user, setUser] = useState([]);



    const { id } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/api/user/${auth.user.username}`)
            .then(response => response.json())
            .then(data => {
                setUser(data);
            })
            .catch(console.log)
    }, [])



    const REVIEW_DEFAULT = {
        appUserId: 0,
        productId: id,
        content: "",
        date: ""
    }


    const history = useHistory();

    const [review, setReview] = useState(REVIEW_DEFAULT);
    

    const handleSubmit = (event) => {
        event.preventDefault();

        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(review)
        }

        fetch('http://localhost:8080/api/reviews', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json()
                } else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => {
                if (data) {
                    history.push("/products");
                } else {
                    //set errors
                }
            })
            .catch(console.log);
    }


    const handleChange = (event) => {
        const newReview = {...review}

        newReview.appUserId = user.appUserId;

        newReview[event.target.name] = event.target.value;

        setReview(newReview);
    }

    console.log(review)

    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <div className="form-content">
                    <h3 className="form-title">Edit Review</h3>
                    <div className="form-group mt-3">
                        <label>Review:</label>
                        <input
                            type="text"
                            id="content"
                            name="content"
                            className="form-control mt-1"
                            value={review.content}
                            onChange={handleChange}
                        />
                        <label>Date:</label>
                        <input
                            type="date"
                            id="date"
                            name="date"
                            className="form-control mt-1"
                            value={review.date}
                            onChange={handleChange}
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

export default TrustReviewForm;