import React from "react";
import { electronicstwolevel } from "../../../data/category/level-two/electronicstwolevel.ts";
import { manleveltwo } from "../../../data/category/level-two/manleveltwo.ts";
import { womenleveltwo } from "../../../data/category/level-two/womenleveltwo.ts";
import { furnituretwolevel } from "../../../data/category/level-two/furnituretwolevel.ts";
import { manthreelevel } from "../../../data/category/level-three/manthreelevel.ts";
import { womenthreelevel } from "../../../data/category/level-three/womenthreelevel.ts";
import { electronicsthreelevel } from "../../../data/category/level-three/electronicsthreelevel.ts";
import { furniturethreelevel } from "../../../data/category/level-three/furniturethreelevel.ts";
import { Box } from "@mui/material";
import zIndex from "@mui/material/styles/zIndex";




const categoryTwo:{[key:string]:any} = {
    men: manleveltwo,
    electronics: electronicstwolevel,
    women: womenleveltwo,
    home_furniture: furnituretwolevel


}
const categoryThree:{[key:string]:any} = {
    men: manthreelevel,
    electronics: electronicsthreelevel,
    women: womenthreelevel,
    home_furniture: furniturethreelevel


}

const CategorySheet = ({selectedCategory, setShowSheet}: any) => {

    const childcategory = (category: any, parentCategoryId: any) => {
        return category.filter((child: any) => child.parentCategoryId == parentCategoryId)
    }

    return (
        <Box sx={{ zIndex: 1 }} className=" bg-white shadow-lg lg:h-[500px] overflow-y-auto ">
            <div className='flex text-sm flex-wrap'>

                {
                    categoryTwo[selectedCategory]?.map((item,index) => <div className={`p-8 lg:w-[20%] ${index%2===0?" bg-slate-50": "bg-white"}`}>

                        <p className="text-primary-color mb-5 font-semibold">{item.name}</p>
                        <ul className='space-y-3'>
                            {childcategory(categoryThree[selectedCategory], item.categoryId).map((item:any) => <div>
                                <li className='hover: text-second-color cursor-pointer'>
                                    {item.name}
                                </li>

                            </div>)
                            }

                        </ul>
                    </div>)
                }
            </div>
        </Box>
    )
}

export default CategorySheet