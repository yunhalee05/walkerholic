import React from 'react'
import { useDispatch, useSelector } from 'react-redux'

import earth from '../../images/earth.svg'
import { addCart, createCart } from '../../_actions/OrderActions'
import Rating from './Rating'

function ProductCard({products}) {
    // product.imageUrl  ? product.imageUrl :
    const dispatch = useDispatch()

    const cart = useSelector(state => state.cart)

    const handleAddToCart = (product) =>{
        if(cart.id){
            dispatch(addCart(1, product.price, product.id,cart.id))
        }else{
            dispatch(createCart()).then(res=>{
                dispatch(addCart(1, product.price, product.id, res))
            })
        }
    }
    return (
        <div className="productcard_container">
            {
                products.map((product,index)=>(
                    <div className="productcard">
                        <div className="productcard_name">
                            {product.name}
                        </div>
                        <div className="productcard_image">
                            <img src={ earth} alt="" />
                        </div>
                        <div className="productcard_price">
                            <div>{product.price}$</div>
                            <Rating rating={product.average}/>
                        </div>
                        <div className="productcard_stock">
                            {
                                product.stock>0 
                                ? <button onClick={()=>handleAddToCart(product)}>Add to cart</button>
                                :'Out of Stock'
                            }
                        </div>
                    </div>
                ))
            }

        </div>
    )
}

export default ProductCard
