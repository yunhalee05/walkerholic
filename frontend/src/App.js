import {BrowserRouter , Route} from 'react-router-dom'
import Header from './components/header/Header';
import Footer from './components/Footer';
import HomeScreen from './screens/HomeScreen';
import LoginScreen from './screens/LoginScreen';
import RegisterScreen from './screens/RegisterScreen';
import ProfileScreen from './screens/ProfileScreen';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { auth } from './_actions/AuthActions';
import axios from 'axios';
import { GET_AUTH_FOLLOWS } from './_constants/AuthConstants';
import DiscoverScreen from './screens/DiscoverScreen';
import PostScreen from './screens/PostScreen';
import PostDetailScreen from './screens/PostDetailScreen';
import ActivityScreen from './screens/ActivityScreen';
import ActivityDetailScreen from './screens/ActivityDetailScreen';
import PostsScreen from './screens/PostsScreen';
import ProductScreen from './screens/ProductScreen'
import ProductDetailScreen from './screens/ProductDetailScreen'
import { GET_CARTITEMS_SUCCESS } from './_constants/OrderConstants';
import CartScreen from './screens/CartScreen';
import OrderScreen from './screens/PlaceOrderScreen';
import PlaceOrderScreen from './screens/PlaceOrderScreen';
import AboutUsScreen from './screens/AboutUsScreen';

function App() {

  const dispatch = useDispatch()
  
  useEffect(() => {
    if(localStorage.getItem("walkerholic_token")){
      const token = localStorage.getItem("walkerholic_token").replace(/\"/gi, "")
      dispatch(auth(token)).then(async(id)=>{
        const res1 = await axios.get(`/follows/${id}`)
        dispatch({
          type:GET_AUTH_FOLLOWS,
          payload:res1.data
        })
        const res2 = await axios.get(`/cartItems/${id}`)
        dispatch({
          type:GET_CARTITEMS_SUCCESS,
          payload:res2.data
        })
      })
    }
  }, [dispatch])

  return (
    <BrowserRouter>
      <div className="App">
          <Header/>

          <div className="main">
            <Route exact path="/" component={HomeScreen}/>
            <Route exact path="/signin" component={LoginScreen}/>
            <Route exact path="/signup" component={RegisterScreen}/>
            <Route exact path="/user/:id" component={ProfileScreen}/>
            <Route exact path="/posts" component={PostsScreen}/>
            <Route exact path="/posts/discover" component={DiscoverScreen}/>
            <Route exact path="/posts/:id" component={PostScreen}/>
            <Route exact path="/post/:id" component={PostDetailScreen}/>
            <Route exact path="/activities" component={ActivityScreen}/>
            <Route exact path="/activity/:id" component={ActivityDetailScreen}/>
            <Route exact path="/products" component={ProductScreen}/>
            <Route exact path="/products/:sort/:category" component={ProductScreen}/>
            <Route exact path="/product/:id" component={ProductDetailScreen}/>
            <Route exact path="/cart/:id" component={CartScreen}/>
            <Route exact path="/placeOrder/:id" component={PlaceOrderScreen}/>
            <Route exact path="/about" component={AboutUsScreen}/>
          </div>
          <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;
