import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getProduct } from '../_actions/ProductActions'
import ProductCarousel from '../components/product/ProductCarousel'
import Rating from '../components/product/Rating'
import ReviewCard from '../components/product/ReviewCard'
import { addCart, createCart } from '../_actions/OrderActions'
 
function ProductDetailScreen(props) {

    const id = props.match.params.id

    const [showDescription, setShowDescription] = useState(true)
    const [qty, setQty] = useState(1)

    const products = useSelector(state => state.products)
    const {product} = products
    const cart = useSelector(state => state.cart)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getProduct(id))
    }, [dispatch])

    const handleQty = (button) =>{
        if(button==="plus"){
            const plusQty = qty+1
            if(plusQty<= product.stock){
                return setQty(plusQty)
            }else return 
        }else if(button ==="minus"){
            const minusQty = qty-1
            if(minusQty>=1){
                return setQty(minusQty)
            }else{
                return 
            }
        }else{
            if(button<1){
                return window.alert('Quantity should be more than 1.')
            }else if(button > product.stock){
                return window.alert('Quantity can not be more than stock.')
            }else{
                return setQty(button)
            }
        }
    }

    const handleAddToCart = () =>{
        if(cart.id){
            dispatch(addCart(qty, product.id,cart.id))
        }else{
            dispatch(createCart()).then(res=>{
                dispatch(addCart(qty, product.id, res))
            })
        }
    }

    return (
        <>
        {products.loading===false &&

        <div className="productdetail">
            <div className="productdetail_container">
                <div className="productdetail_images">
                    <ProductCarousel images={product.productImages}/>
                </div>
                <div className="productdetail_info">
                    <div className="productdetail_name">
                        {product.name}
                    </div>
                    <div className="productdetail_price">
                        <div>
                            {product.price}$
                        </div>
                        <Rating rating={product.average}/>
                    </div>
                    <div className="productdetail_cart">
                        <div style={{border:"1px solid #dbdbdb", padding:"10px", borderRadius:"10px"}}>
                            <span><i className="fas fa-minus" onClick={()=>handleQty("minus")}></i></span>
                            <input  type="number" value={qty} onChange={(e)=>handleQty(e.target.value)}/>
                            <span><i className="fas fa-plus" onClick={()=>handleQty("plus")}></i></span>
                        </div>
                        <div>
                            <button onClick={handleAddToCart}>Add to Cart</button>
                        </div>
                    </div>
                    <hr />
                    <div className="productdetail_description">
                        <div style={{display:"flex", justifyContent:"space-between",alignItems:"center"}}>
                            <div>Description</div>
                            <div onClick={()=>setShowDescription(!showDescription)}><i className={showDescription? "fas fa-minus":"fas fa-plus"} ></i></div>
                        </div>
                        {
                            showDescription &&
                            <div>
                                <div className="productdetail_seller">
                                    {product.user.fullname}
                                </div>
                                <div>{product.description}</div>
                            </div>
                        }
                    </div>
                    <hr />
                </div>
            </div>
            <div className="productdetail_review">
                {
                    product.productReviews.length===0
                    ? <div>No reviews yet.</div> 
                    : <div>
                        {
                            product.productReviews.map((review,index)=>(
                                <ReviewCard review={review} key={index}/>
                            ))
                        }
                    </div>
                }
            </div>
        </div>
        }
        </>
    )
}

export default ProductDetailScreen
