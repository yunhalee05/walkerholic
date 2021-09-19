import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import Category from '../components/product/Category'
import ProductCard from '../components/product/ProductCard'
import Sort from '../components/product/Sort'
import { getProducts } from '../_actions/ProductActions'

function ProductScreen(props) {

    const c = props.match.params.category
    const s = props.match.params.sort

    const [page, setPage] = useState(1)
    const [category, setCategory] = useState(c? c : '')
    const [sort, setSort] = useState(s? s :'')
    const [keyword, setKeyword] = useState('')

    const products = useSelector(state => state.products)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getProducts(1, sort, category,keyword))
    }, [dispatch, sort, category, keyword])

    return (
        <div className="productscreen">
            {   
                products.products &&
                <div>
                    <Category setCategory={setCategory}/>

                    <Sort setSort={setSort}/>

                    <ProductCard products={products.products}/>
                </div>
            }

        </div>
    )
}

export default ProductScreen
