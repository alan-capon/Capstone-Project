import "./TrustReviewList.css";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

function TrustReviewList() {
    const { id } = useParams();
    const [reviews, setReviews] = useState([]);

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

    return (
        <>
            <div className="Products">
                <h1 className="Title">TrustedReviews</h1>
            </div>
            <div class="container-fluid">
                <div class="mgb-40 padb-30 auto-invert line-b-4 align-center ">
                    <h1 class="fg-text-d lts-md fs-300 fs-300-xs no-mg" >Read Customer Reviews</h1>
                </div>
                <ul class="hash-list cols-3 cols-1-xs pad-30-all align-center text-sm">
                    {reviews.map(review => (
                        <li key={review.id}>
                        <h3 class="font-cond mgb-5 fg-text-d fs-130" >{review.firstName} {review.lastName}</h3>
                        <h5 class="font-cond mgb-5 fg-text-d fs-130" >{review.username}</h5>
                        <img src="https://bootdey.com/img/Content/avatar/avatar1.png" class="wpx-100 img-round mgb-20" title="" alt="" data-edit="false" data-editor="field" data-field="src[Image Path]; title[Image Title]; alt[Image Alternate Text]" />
                        <p class="fs-110" contenteditable="false">"{review.content}"</p>
                        <h5 class="font-cond mgb-5 fg-text-d fs-130" >Martha Stewart</h5>
                        <small class="font-cond case-u lts-sm fs-80 fg-text-l">{review.date}</small>
                    </li>
                    ))}
                    
                    {/* <li>
                        <img src="https://bootdey.com/img/Content/avatar/avatar4.png" class="wpx-100 img-round mgb-20" title="" alt="" data-edit="false" data-editor="field" data-field="src[Image Path]; title[Image Title]; alt[Image Alternate Text]"/>
                            <p class="fs-110 font-cond-l" contenteditable="false">" Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae. "</p>
                            <h5 class="font-cond mgb-5 fg-text-d fs-130" contenteditable="false">Ariana Menage</h5>
                            <small class="font-cond case-u lts-sm fs-80 fg-text-l" contenteditable="false">Recording Artist - Los Angeles</small>
                    </li>
                    <li>
                        <img src="https://bootdey.com/img/Content/avatar/avatar5.png" class="wpx-100 img-round mgb-20" title="" alt="" data-edit="false" data-editor="field" data-field="src[Image Path]; title[Image Title]; alt[Image Alternate Text]"/>
                            <p class="fs-110 font-cond-l" contenteditable="false">" Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae. "</p>
                            <h5 class="font-cond mgb-5 fg-text-d fs-130" contenteditable="false">Sean Carter</h5>
                            <small class="font-cond case-u lts-sm fs-80 fg-text-l" contenteditable="false">Fund Manager - Chicago</small>
                    </li> */}
                </ul>
            </div>
        </>
    )

}

export default TrustReviewList;