import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

response_list = WS.sendRequestAndVerify(findTestObject('GET - List Users'))

WS.verifyResponseStatusCode(response_list, 200)

def json_list = new JsonSlurper().parseText(response_list.getResponseText())

def total_data_page = json_list.data.size()

Random rand = new Random()

int randIndex = rand.nextInt(total_data_page)

println(randIndex)

GlobalVariable.id = json_list.data[randIndex].id

response_detail = WS.sendRequestAndVerify(findTestObject('GET - Single User'))

WS.verifyResponseStatusCode(response_detail, 200)

def json_detail = new JsonSlurper().parseText(response_detail.getResponseText())

GlobalVariable.userId = json_detail.data.id

response_update = WS.sendRequestAndVerify(findTestObject('PUT - Update Data User'))

WS.verifyResponseStatusCode(response_update, 200)

def json_put = new JsonSlurper().parseText(response_update.getResponseText())

WS.verifyElementPropertyValue(response_update, 'name', json_put.name)

WS.verifyElementPropertyValue(response_update, 'job', json_put.job)

response_delete = WS.sendRequestAndVerify(findTestObject('DELETE - Data User'))

WS.verifyResponseStatusCode(response_delete, 204)

def response_delete_body = response_delete.getResponseBodyContent()

WS.verifyEqual(response_delete_body, '')

