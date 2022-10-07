import "./TrustReviewList.css";
import { Link, useParams } from "react-router-dom";
import { useEffect, useState, useContext } from "react";
import AuthContext from "../AuthContext";

function TrustReviewList() {
    const { id } = useParams();
    const [reviews, setReviews] = useState([]);
    const auth = useContext(AuthContext);
    const [likes, setLikes] = useState(0);
    const [dislikes, setDislikes] = useState(0);



    useEffect(() => {
        if (id) {
            fetch(`http://localhost:8080/api/reviews/${id}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json()
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setReviews(data))
                .catch(console.log)
        }
    }, [id])


    const handleDelete = (reviewId) => {
        if (window.confirm("Are you sure you want to delete your review")) {
            const init = {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${auth.user.token}`
                }
            }

            fetch(`http://localhost:8080/api/reviews/${reviewId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newReviews = reviews.filter(review => review.id !== reviewId)
                        setReviews(newReviews);
                    } else {
                        return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                    }
                })
                .catch(console.log);
        }
    }

    return (
        <>
            <div className="Products">
                <h1 className="Title m-5">Read Customer Reviews</h1>
            </div>
            <div class="container-fluid">
                {/* <div class="mgb-40 padb-30 auto-invert line-b-4 align-center ">
                    <h1 class="fg-text-d lts-md fs-300 fs-300-xs no-mg" >Read Customer Reviews</h1>
                </div> */}
                <ul class="hash-list cols-3 cols-1-xs pad-30-all align-center text-sm">
                    {reviews.map(review => (
                        <li key={review.id}>
                            <h3 class="font-cond mgb-5 fg-text-d fs-130" >{review.firstName} {review.lastName}</h3>
                            <h5 class="font-cond mgb-5 fg-text-d fs-130" >{review.username} </h5>
                            <h6 class="font-cond mgb-5 fg-text-d fs-130"> Credibility Rating: {70 + (likes * 2) - (dislikes * 3)}/100</h6>
                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" class="wpx-100 img-round mgb-20" title="" alt="Profile Picture" />
                            <p class="fs-110" contenteditable="false">"{review.content}"</p>
                            <h5 class="font-cond mgb-5 fg-text-d fs-130" ></h5>
                            <small class="font-cond case-u lts-sm fs-80 fg-text-l">{review.date}</small>
                            <div className="container-flex" >
                                <figure>
                                    <img src="/thumbsup.png" width="30" height="30" alt="Like Review" className="thumbsup"
                                        onClick={() => setLikes(likes + 1)}
                                    />
                                    <figcaption>{likes || "Helpful"}</figcaption>
                                </figure>
                                <figure>
                                    <img src="/thumbsdown.png" width="30" alt="Dislike Review" className="thumbsdown"
                                        onClick={() => setDislikes(dislikes + 1)}
                                    />
                                    <figcaption className="dislike">{dislikes || "Not Helpful"}</figcaption>
                                </figure>
                            </div>

                            {auth.user && auth.user.username === review.username && (
                                <div className="links">
                                    <Link className="edit" style={{ textDecoration: 'none' }} to={`/review/edit/${review.id}`}>Edit</Link>
                                    <Link className="delete" style={{ textDecoration: 'none' }} onClick={() => handleDelete(review.id)}>Delete</Link>
                                </div>
                            )}
                        </li>
                    ))}
                </ul>



            </div>

        </>
    )

}

export default TrustReviewList;


// {/* <li>
//                         <img src="https://bootdey.com/img/Content/avatar/avatar4.png" class="wpx-100 img-round mgb-20" title="" alt="" data-edit="false" data-editor="field" data-field="src[Image Path]; title[Image Title]; alt[Image Alternate Text]"/>
//                             <p class="fs-110 font-cond-l" contenteditable="false">" Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae. "</p>
//                             <h5 class="font-cond mgb-5 fg-text-d fs-130" contenteditable="false">Ariana Menage</h5>
//                             <small class="font-cond case-u lts-sm fs-80 fg-text-l" contenteditable="false">Recording Artist - Los Angeles</small>
//                     </li>
//                     <li>
//                         <img src="https://bootdey.com/img/Content/avatar/avatar5.png" class="wpx-100 img-round mgb-20" title="" alt="" data-edit="false" data-editor="field" data-field="src[Image Path]; title[Image Title]; alt[Image Alternate Text]"/>
//                             <p class="fs-110 font-cond-l" contenteditable="false">" Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae. "</p>
//                             <h5 class="font-cond mgb-5 fg-text-d fs-130" contenteditable="false">Sean Carter</h5>
//                             <small class="font-cond case-u lts-sm fs-80 fg-text-l" contenteditable="false">Fund Manager - Chicago</small>
//                     </li> */}