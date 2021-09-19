import React from 'react'

function ProductCarousel({images}) {

    const isActive = (index) =>{
        if(index === 0) return "active"
    }

    return (
        <div className="productcarousel">
            <div id="mainImage" className="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    {
                        images.map((image, index)=>(
                            <div className={`carousel-item ${isActive(index)}`} key={index}>
                                {
                                    image.imageUrl.match(/video/i)||image.imageUrl.match(/mp4/i)||image.imageUrl.match(/avi/i)||image.imageUrl.match(/mov/i)||image.imageUrl.match(/wmv/i)
                                    ? <video src={image.imageUrl} controls className="d-block w-100" alt={image.data}></video>
                                    : <img src={image.imageUrl} className="d-block w-100" alt={image.data} />
                            }
                            </div>
                        ))
                    }
                </div>
                <ol className="productcarousel-indicators">
                    {
                        images.map((image, index)=>(
                            <div className="productcarousel-indicator">
                                <li data-target="#mainImage" data-slide-to={index} className={isActive(index)} key={index}>
                                    <img src={image.imageUrl} alt={image.name} />
                                </li>
                            </div>
                        ))
                    }
                </ol>
            </div>
        </div>
    )
}

export default ProductCarousel
