<script setup>
    import TopLabelTextBox from '../components/TopLabelTextBox';
    import Button from '../components/Button';

</script>

<template>
    <div id="background">
        <div id="login_container">
            <div id="logo_container">
                <img id="logo" src="../assets/img/logo.png" alt="logo cora duarte" srcset="">
            </div>
            <div id="form_container">
                <TopLabelTextBox label="Login"/>
                <TopLabelTextBox label="Senha"/>
                <Button id="Button_Login" label="Entrar" />
            </div>
        </div>
    </div>
</template>

<style scoped>

    *{
        font-size: 10px;
    }

    #background{
        background: rgb(38,130,220);
        background: linear-gradient(145deg, rgba(38,130,220,1) 10%, rgb(10, 51, 73) 100%);
        width: 100vw;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        
    }

    #login_container{
        width: 50vh;
        height: 60vh;
        border-radius: 30px;
        background-color: rgb(255, 255, 255);
        box-shadow: rgba(17, 12, 46, 0.4) 10px 10px 50px 25px;
        display: flex;
        align-items: center;
        flex-direction: column;
    }

    #logo_container{
        margin-top: 40px;
        width: 60%;
        height: auto;
    }

    #logo{
        width: 100%;
    }

    #form_container{
        width: 85%;
        height: 65%;
        margin-top: 30px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    #Button_Login{
        margin-top:10%
    }
    
</style>