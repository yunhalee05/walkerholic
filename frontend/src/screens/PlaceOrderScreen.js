import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router'

function PlaceOrderScreen(props) {

    const id = props.match.params.id

    const cart = useSelector(state => state.cart)
    const history = useHistory()

    useEffect(() => {
        if(!cart.checkout){
            history.push(`/`)
        }
    }, [])

    const handleSubmit=()=>{

    }

    const subtotal = parseFloat(cart.orderItems?.reduce((a,c)=> a+ c.productPrice*c.qty,0))
    const shipping = subtotal>100 ? parseFloat(0) : parseFloat(5)
    const total = subtotal + shipping 

    return (
        <>
        {
            cart.loading ===false &&
            <div className="placeorder">
                <div className="placeorder_info">
                        <form onSubmit={handleSubmit}>
                            <div className="placeorder_address">
                                <div className="placeorder_subtitle">🏠 Shipping Address </div>
                                <input type="text" placeholder="Address Name"/>
                                <input type="text" placeholder="Address"/>
                                <input type="text" placeholder="Country"/>
                                <input type="text" placeholder="City"/>
                                <input type="text" placeholder="Zip Code"/>
                            </div>
                            <hr/>
                            <div className="placeorder_payment">
                                <div className="placeorder_subtitle">💸 Payment </div>

                            </div>
                        </form>
                </div>
                <div className="placeorder_summary">
                    <div className="placeorder_items">
                        {
                            cart.orderItems.map((item,index)=>(
                                <div key={index} className="placeorder_item">
                                    <div style={{display:"flex",justifyContent:"flex-start", alignItems:"center"}}>
                                        <div className="placeorder_item_qty">
                                            {item.qty}
                                        </div>
                                        <div className="placeorder_item_image">
                                            <img src={item.productImageUrl} alt="" />
                                        </div>
                                        <div className="placeorder_item_name">
                                            {item.productName}
                                        </div>
                                    </div>
                                    <div className="placeorder_item_price">
                                        {parseFloat(item.productPrice* item.qty).toFixed(2)} $
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                    <hr/>
                    <div className="placeorder_price">
                        <div className="cart_info">
                            <div className="cart_info_label" style={{fontWeight:"600", fontSize:"15px"}}>Subtotal</div>
                            <div style={{fontSize:"15px"}} >{subtotal.toFixed(2)}$</div>
                        </div>
                        <div className="cart_info">
                            <div className="cart_info_label" style={{fontWeight:"600", fontSize:"15px"}}>Shipping</div>
                            <div style={{fontSize:"15px"}}>{shipping.toFixed(2)}$</div>
                        </div>
                        <hr/>
                        <div className="cart_info" style={{marginBottom:"1.6rem"}}>
                            <div className="cart_info_label">Estimated Total</div>
                            <div style={{fontSize:"1.2rem"}}>{total.toFixed(2)}$</div>
                        </div>
                    </div>
                </div>
            </div>
        }
        </>
    )
}

export default PlaceOrderScreen
