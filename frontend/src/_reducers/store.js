import{combineReducers, createStore, compose, applyMiddleware} from 'redux';
import thunk from 'redux-thunk'
import { authReducer } from './AuthReducers';

const initialState = {

}
// const initialState={
//     auth : {
//         ...localStorage.getItem('token')? {token:JSON.parse(localStorage.getItem('token')} : null
//     }
// }

const reducer = combineReducers({
    auth : authReducer,

})

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__||compose;
const store = createStore(reducer, initialState, composeEnhancer(applyMiddleware(thunk)));
export default store;