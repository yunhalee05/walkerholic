import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { useHistory } from 'react-router'

function Category({setCategory}) {

    const history = useHistory()

    const [categories, setCategories] = useState([])

    const getCategories = async() =>{
        await axios.get('/categories').then(res=>{
            setCategories(res.data)
        })
    }

    useEffect(() => {
        getCategories()
    }, [])


    return (
        <div className="product_category">
        {
            categories.map((c,index)=>(
                <div>
                    <span style={{cursor:"pointer"}} key={index} onClick={()=>setCategory(c)}>
                        {c} 
                    </span>
                    {
                        index<categories.length-1 &&
                        <span>|</span>
                    }
                </div>
            ))
        }
        </div>
    )
}

export default Category
