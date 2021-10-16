import axios from "axios"
import { ADD_TO_CART_FAIL, ADD_TO_CART_REQUEST, ADD_TO_CART_SUCCESS, CREATE_CART_FAIL, CREATE_CART_REQUEST, CREATE_CART_SUCCESS, CREATE_ORDER_FAIL, CREATE_ORDER_REQUEST, CREATE_ORDER_SUCCESS, DELETE_ORDERITEM_FAIL, DELETE_ORDERITEM_REQUEST, DELETE_ORDERITEM_SUCCESS, GET_CARTITEMS_FAIL, GET_CARTITEMS_REQUEST, GET_CARTITEMS_SUCCESS, GET_ORDER_FAIL, GET_ORDER_LIST_FAIL, GET_ORDER_LIST_REQUEST, GET_ORDER_LIST_SUCCESS, GET_ORDER_REQUEST, GET_ORDER_SUCCESS, UPDATE_ORDERITEM_QTY_FAIL, UPDATE_ORDERITEM_QTY_REQUEST, UPDATE_ORDERITEM_QTY_SUCCESS } from "../_constants/OrderConstants"

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
        const res = await axios.post(`/createCart/${user.id}`)

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

export const addCart = (qty, productId, orderId) =>async(dispatch, getState)=>{

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
                productId:existOrderItem[0].productId,
                orderId:orderId
            }
        }else{
            orderItem={
                qty:qty,
                productId:productId,
                orderId:orderId
            }
        }
    }else{
    orderItem={
        qty:qty,
        productId:productId,
        orderId:orderId
        }
    }


    dispatch({
        type:ADD_TO_CART_REQUEST
    })

    try{
        const res = await axios.post(`/addToCart/${orderId}`, orderItem)

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


export const updateQty = (id, qty) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:UPDATE_ORDERITEM_QTY_REQUEST
    })


    try{
        await axios.post(`/updateQty/${id}?qty=${qty}`,null)

        dispatch({
            type:UPDATE_ORDERITEM_QTY_SUCCESS,
            payload:{
                id:id,
                qty:qty
            }
        })


    }catch(error){
        dispatch({
            type:UPDATE_ORDERITEM_QTY_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}


export const deleteOrderItem = (id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:DELETE_ORDERITEM_REQUEST
    })


    try{
        await axios.delete(`/deleteOrderItem/${id}`)

        dispatch({
            type:DELETE_ORDERITEM_SUCCESS,
            payload:id
        })


    }catch(error){
        dispatch({
            type:DELETE_ORDERITEM_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}



export const getOrderList = (page) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:GET_ORDER_LIST_REQUEST
    })


    try{
        const res = await axios.get(`/orderlist/${page}`)

        dispatch({
            type:GET_ORDER_LIST_SUCCESS,
            payload: res.data
        })


    }catch(error){
        dispatch({
            type:GET_ORDER_LIST_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}

export const getOrderListBySeller = (page,id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:GET_ORDER_LIST_REQUEST
    })


    try{
        const res = await axios.get(`/orderlist/${page}/${id}`)

        dispatch({
            type:GET_ORDER_LIST_SUCCESS,
            payload: res.data
        })


    }catch(error){
        dispatch({
            type:GET_ORDER_LIST_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}

export const createOrder = (orderCreateDTO) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:CREATE_ORDER_REQUEST
    })


    try{
        await axios.post('/payOrder', orderCreateDTO)
        dispatch({
            type:CREATE_ORDER_SUCCESS
        })


    }catch(error){
        dispatch({
            type:CREATE_ORDER_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}

export const getOrder = (id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:GET_ORDER_REQUEST
    })


    try{
        const res = await axios.get(`/order/${id}`)
        dispatch({
            type:GET_ORDER_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_ORDER_FAIL,
            payload:error.response.data
            
        })
        // console.log(error)
    }
}
