import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import CartProductCard from '../components/cart/CartProductCard'

function CartScreen(props) {

    const id = props.match.params.id

    const cart = useSelector(state => state.cart)

    const dispatch = useDispatch()

    const subtotal = parseFloat(cart.orderItems.reduce((a,c)=> a+ c.productPrice*c.qty,0))
    const shipping = subtotal>100 ? parseFloat(0) : parseFloat(5)
    const total = subtotal + shipping 

    return (
        <>
        {
            cart.loading===false &&
            <div className="cartscreen">                
                <div className="cart_products">
                    <div style={{textAlign:"right"}}>Total Items : {cart.orderItems.reduce((a, c)=> a+c.qty, 0)}</div>
                {
                    cart.orderItems.length===0 
                    ? <span>No Items.</span>
                    :   cart.orderItems.map((item,index)=>(
                            <CartProductCard product={item} key={index} />
                        ))
                }
                </div>

                <div className="cart_info_container">
                    <div className="cart_info">
                        <div>Subtotal</div>
                        <div>{subtotal.toFixed(2)}$</div>
                    </div>
                    <div className="cart_info">
                        <div>Shipping</div>
                        <div>{shipping.toFixed(2)}$</div>
                    </div>
                    <div className="cart_info">
                        <div>Estimated Total</div>
                        <div>{total.toFixed(2)}$</div>
                    </div>
                    <div className="form_button">
                        <button>Checkout</button>
                    </div>
                </div>

            </div>
            
        }
        </>
    )
}

export default CartScreen
