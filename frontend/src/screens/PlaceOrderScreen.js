import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router'

function PlaceOrderScreen(props) {

    const id = props.match.params.id

    const cart = useSelector(state => state.cart)
    const history = useHistory()

    useEffect(() => {
        if(!cart.checkout){
            history.push(`/cart/${id}`)
        }
    }, [])

    return (
        <>
        {
            cart.loading ===false &&
            <div className="placeorder">
                <div className="placeorder_info">
                    <div className="placeorder_payment">

                    </div>
                    <div className="placeorder_address">
                        <form action="">
                            <div>
                                <label>Address Name</label>
                                <input type="text" />
                            </div>
                            <div>
                                <label>Address</label>
                                <input type="text" />
                            </div>
                            <div>
                                <label>Country</label>
                                <input type="text" />
                            </div>
                            <div>
                                <label>City</label>
                                <input type="text" />
                            </div>
                            <div>
                                <label>Zip code</label>
                                <input type="text" />
                            </div>
                        </form>
                    </div>
                </div>
                <div className="placeorder_summary">
                    <div className="placeorder_items">

                    </div>
                    <div className="placeorder_price">

                    </div>
                </div>
            </div>
        }
        </>
    )
}

export default PlaceOrderScreen
