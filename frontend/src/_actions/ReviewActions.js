import axios from "axios"
import { CREATE_REVIEW_FAIL, CREATE_REVIEW_REQUEST, CREATE_REVIEW_SUCCESS, DELETE_REVIEW_FAIL, DELETE_REVIEW_REQUEST, DELETE_REVIEW_SUCCESS, EDIT_REVIEW_FAIL, EDIT_REVIEW_REQUEST, EDIT_REVIEW_SUCCESS } from "../_constants/ReviewConstants"

export const createReview = (rating, comment, productId) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:CREATE_REVIEW_REQUEST
    })

    const reviewCreateDTO = {
        rating:rating,
        comment:comment,
        productId:productId,
        userId:user.id
    }

    try{
        const res = await axios.post('/review/save',reviewCreateDTO)

        console.log(res)
        dispatch({
            type:CREATE_REVIEW_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:CREATE_REVIEW_FAIL,
            payload:error.response.data
            
        })
    }
}

export const editReview = (reviewCreateDTO) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:EDIT_REVIEW_REQUEST
    })

    try{
        const res = await axios.post('/review/save',reviewCreateDTO)

        console.log(res)
        dispatch({
            type:EDIT_REVIEW_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:EDIT_REVIEW_FAIL,
            payload:error.response.data
            
        })
    }
}
export const deleteReview = (id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:DELETE_REVIEW_REQUEST
    })

    try{
        
        await axios.delete(`/review/delete/${id}`)

        dispatch({
            type:DELETE_REVIEW_SUCCESS,
            payload:id
        })

    }catch(error){
        dispatch({
            type:DELETE_REVIEW_FAIL,
            payload:error.response.data
            
        })
    }
}
