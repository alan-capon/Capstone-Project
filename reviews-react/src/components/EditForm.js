import { useEffect, useState, useContext } from "react";
import { useParams, useHistory } from "react-router-dom";
import AuthContext from "../AuthContext";




function EditForm() {
    const { id } = useParams();
    const auth = useContext(AuthContext);
    

    

    const REVIEW_DEFAULT = {
        appUserId: 0,
        productId: id,
        content: "",
        date: ""
    }

    const history = useHistory();

    const [review, setReview] = useState(REVIEW_DEFAULT);

    useEffect(() => {
        if(id) {
            fetch(`http://localhost:8080/api/reviews/product/${id}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setReview(data))
                .catch(console.log)                
        }
    }, [id])


    const handleChange = (event) => {
        const newReview = {...review}

        newReview[event.target.name] = event.target.value;

        setReview(newReview);
    }



    const handleSubmit = (event) => {
        event.preventDefault();

        const init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(review)
        }

        fetch(`http://localhost:8080/api/reviews/${id}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null
                } else if (response.status === 400) {
                    return response.json()
                } else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .then (data => {
                if(!data) {
                    history.push("/products")
                } else {
                    //set errors
                }
            })
            .catch(console.log)
    }

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

export default EditForm;