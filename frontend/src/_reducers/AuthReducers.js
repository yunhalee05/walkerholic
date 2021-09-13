import { AUTH_FAIL, AUTH_REQUEST, AUTH_SUCCESS, LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS, REGISTER_FAIL, REGISTER_REQUEST, REGISTER_SUCCESS } from "../_constants/AuthConstants";

export const authReducer = (state={}, action)=>{
    switch(action.type){
        case REGISTER_REQUEST:
            return {...state, loading:true}
        case REGISTER_SUCCESS:
            return {...state, loading:false, user:action.payload.user, token:action.payload.token}
        case REGISTER_FAIL:
            return {...state, loading:false, error:action.payload}

        case LOGIN_REQUEST:
            return {...state, loading:true}
        case LOGIN_SUCCESS:
            return {...state, loading:false, user:action.payload.user, token:action.payload.token}
        case LOGIN_FAIL:
            return {...state, loading:false, error:action.payload}
    
        case AUTH_REQUEST:
            return {...state, loading:true}
        case AUTH_SUCCESS:
            return {...state, loading:false, user:action.payload.user, token:action.payload.token}
        case AUTH_FAIL:
            return {...state, loading:false, error:action.payload}
    
        default:
            return state;
    }
}