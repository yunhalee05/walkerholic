import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { getCart } from '../_actions/OrderActions'

function CartScreen(props) {

    const id = props.match.params.id

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getCart(id))
    }, [dispatch])

    
    return (
        <div>
            
        </div>
    )
}

export default CartScreen
