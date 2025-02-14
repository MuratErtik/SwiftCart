import { Divider, ListItemIcon, ListItemText } from "@mui/material";
import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

interface menuItem{
    name:string,
    path:string,
    icon:any,
    activeIcon:any
}

interface DrawerListProp{
    menu:menuItem[],
    menu2:menuItem[],
    toggleDrawer:()=>void
}
const DrawerList = ({menu,menu2,toggleDrawer}:DrawerListProp) =>{
    const location = useLocation();
    const navigation = useNavigate();
    return(
       <div className='h-full'>
            <div className='flex flex-col justify-between h-full w-[300px] border-r py-5'>
                <div>
                    <div className="space-y-3">
                        {
                            menu.map((item:any,index:number) =>
                                
                                    <div onClick={() => navigation(item.path)} className="pr-9 cursor-pointer" key={index}>
                                        <p className={`${item.path==location.pathname?"bg-primary-color text-white":"text-primary-color"}
                                        flex items-center px-5 py-3 rounded-r-full`}>
                                            <ListItemIcon>{item.path==location.pathname?item.activeIcon:item.icon}</ListItemIcon>
                                            <ListItemText primary={item.name} />

                                        </p>
                                    </div>
                                
                            )
                        }
                    </div>
                    <Divider/>
                    <div className="space-y-3">
                        {
                            menu2.map((item:any,index:number) =>
                                
                                    <div onClick={() => navigation(item.path)} className="pr-9 cursor-pointer" key={index}>
                                        <p className={`${item.path==location.pathname?"bg-primary-color text-white":"text-primary-color"}
                                        flex items-center px-5 py-3 rounded-r-full`}>
                                            <ListItemIcon>{item.path==location.pathname?item.activeIcon:item.icon}</ListItemIcon>
                                            <ListItemText primary={item.name} />

                                        </p>
                                    </div>
                                
                            )
                        }
                    </div>
                </div>

            </div>

       </div>
    )
}

export default DrawerList