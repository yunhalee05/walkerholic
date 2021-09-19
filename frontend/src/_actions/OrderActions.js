import axios from "axios"
import { ADD_TO_CART_FAIL, ADD_TO_CART_REQUEST, ADD_TO_CART_SUCCESS, CREATE_CART_FAIL, CREATE_CART_REQUEST, CREATE_CART_SUCCESS, GET_CARTITEMS_FAIL, GET_CARTITEMS_REQUEST, GET_CARTITEMS_SUCCESS } from "../_constants/OrderConstants"

export const getCart = (id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:GET_CARTITEMS_REQUEST
    })

    try{
        const res = await axios.get(`/cartItems/${id}`)

        dispatch({
            type:GET_CARTITEMS_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_CARTITEMS_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}

export const createCart = () =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:CREATE_CART_REQUEST
    })

    try{
        const res = await axios.post('/createCart')

        dispatch({
            type:CREATE_CART_SUCCESS,
            payload:res.data
        })

        return res.data

    }catch(error){
        dispatch({
            type:CREATE_CART_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}

export const addCart = (qty, price, productId, orderId) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()
    const {cart : {orderItems}} = getState()
    var orderItem={};

    if(orderItems){
        const existOrderItem = orderItems.filter(i=>i.productId===productId)
        console.log(existOrderItem)

        if(existOrderItem.length>0){
            orderItem={
                id:existOrderItem[0].id,
                qty:existOrderItem[0].qty+qty,
                price:existOrderItem[0].price+(price*qty),
                productId:existOrderItem[0].productId,
                orderId:orderId
            }
        }else{
            orderItem={
                qty:qty,
                price:(price*qty),
                productId:productId,
                orderId:orderId
            }
        }
    }else{
    orderItem={
        qty:qty,
        price:(price*qty),
        productId:productId,
        orderId:orderId
        }
    }


    dispatch({
        type:ADD_TO_CART_REQUEST
    })

    console.log(orderItem)
    try{
        const res = await axios.post(`/addToCart/${orderId}`, orderItem)

        console.log(res)
        dispatch({
            type:ADD_TO_CART_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:ADD_TO_CART_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}