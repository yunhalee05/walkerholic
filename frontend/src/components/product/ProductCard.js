import React from 'react'

import earth from '../../images/earth.svg'

function ProductCard({product}) {
    return (
        <div>
            {product.name}
            <img src={product.imageUrl  ? product.imageUrl : earth} alt="" />
            {product.price}
            {product.average}
            {product.stock>0 ? 'Add to cart':'Out of Stock'}
        </div>
    )
}

export default ProductCard
