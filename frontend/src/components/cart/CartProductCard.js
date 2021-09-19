import React, { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { deleteOrderItem, updateQty } from '../../_actions/OrderActions'
import QuantityInput from '../QuantityInput'

function CartProductCard({product}) {

    const [qty, setQty] = useState(product.qty)

    const dispatch = useDispatch()

    useEffect(() => {
        if(qty!==product.qty){
            dispatch(updateQty(product.id, qty))
        }
    }, [dispatch, qty])

    const handleDelete = () =>{
        dispatch(deleteOrderItem(product.id))
    }

    return (
        <>
            <div className="cart_productcard">
                <div className="cart_product_image">
                    <img src={product.productImageUrl} alt="" />
                </div>
                <div className="cart_product_info">
                    <div>
                        {product.productName}
                    </div>
                    <QuantityInput qty={qty} setQty={setQty} stock={product.stock}/>
                </div>
                <div className="cart_product_price">
                    <div >
                        <i onClick={handleDelete} className="far fa-trash-alt"></i>
                    </div>
                    <div>
                        {parseFloat(product.productPrice* qty).toFixed(2)} $
                    </div>
                </div>
            </div>
        </>
    )
}

export default CartProductCard
