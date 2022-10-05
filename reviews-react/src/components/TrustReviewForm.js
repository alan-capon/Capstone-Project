import { useState, useEffect } from "react";


const REVIEW_DEFAULT = {
    review: "",
    date: ""
}



function TrustReviewForm() {
    const [review, setReview] = useState(REVIEW_DEFAULT);
    

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${auth.user.token}`
        },
        body: JSON.stringify(product)
    }


   

    const handleChange = (event) => {
        const newReview = {...review}

        newReview[event.target.name] = event.target.value;
        setReview(newReview);
    }


    return (
        <div className="form-container">
            <form className="form" onSubmit={handleSubmit}>
                <div className="form-content">
                    <h3 className="form-title">Add Review</h3>
                    <div className="form-group mt-3">
                        <label>Review:</label>
                        <input
                            type="text"
                            id="review"
                            name="review"
                            className="form-control mt-1"
                            value={review.review}
                            onChange={handleChange}                                                    
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Date Purchased:</label>
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