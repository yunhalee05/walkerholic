import { AUTH_FAIL, AUTH_REQUEST, AUTH_SUCCESS, LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS, REGISTER_FAIL, REGISTER_REQUEST, REGISTER_SUCCESS } from "../_constants/AuthConstants"
import axios from "axios"

export const register = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:REGISTER_REQUEST
    })

    try{
        const res = await axios.post('/signup', bodyFormData)

        dispatch({
            type:REGISTER_SUCCESS,
            payload:res.data
        })

        localStorage.setItem("walkerholic_token", JSON.stringify(res.data.token))

    }catch(error){
        dispatch({
            type:REGISTER_FAIL,
            payload:error.response.data
            
        })
    }
}

export const login = ({email, password}) =>async(dispatch, getState)=>{

    dispatch({
        type:LOGIN_REQUEST
    })

    const body = {
        username:email,
        password:password
    }

    try{
        const res = await axios.post('/signin', body)
        console.log(res)

        dispatch({
            type:LOGIN_SUCCESS,
            payload:res.data
        })

        localStorage.setItem("walkerholic_token", JSON.stringify(res.data.token))

    }catch(error){
        dispatch({
            type:LOGIN_FAIL,
            payload:error.response.data
            
        })
    }
}


export const auth = (token) =>async(dispatch, getState)=>{

    dispatch({
        type:AUTH_REQUEST
    })
    console.log(token)


    try{
        const res = await axios.post(`/authenticate?token=${token}`,null)
        console.log(res)

        dispatch({
            type:AUTH_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:AUTH_FAIL,
            payload:error.response.data
            
        })
    }
}

