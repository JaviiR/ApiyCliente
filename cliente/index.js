/*fetch("http://localhost:8080/sistema/").then(e=>e.json()).then((e)=>{
    console.log(e)
})*/
 const funcionalidad=async()=>{
try {
    resultado= await axios('http://localhost:8080/sistema/').then(e=>console.log(e.data))    
    
} catch (error) {
    console.log("Este es el error: "+error)
}
}

/*try {
    resultado=axios('http://localhost:8080/sitema/').then(e=>console.log(e.data))    
    console.log("terminó")
} catch (error) {
    console.log("Este es el error: "+error)
}*/

funcionalidad();